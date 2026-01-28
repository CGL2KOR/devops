#!/bin/bash

LOG_FILE="/opt/raman-devops/logs/user_creation.log"

exec >> "$LOG_FILE" 2>&1


if [[ $# -ne 2 ]]; then
  echo "Usage: $0 <username> <group>"
  exit 1
fi



USERNAME=$1
GROUP=$2


if [[ $EUID -ne 0 ]]; then
  echo "This script must be run as root"
  exit 1
fi


if ! getent group "$GROUP" >/dev/null; then
  echo "Group $GROUP does not exist"
  exit 1
fi


if id "$USERNAME" &>/dev/null; then
  echo "User $USERNAME already exists"
else
  useradd -m -s /bin/bash -g "$GROUP" "$USERNAME"
  passwd -e "$USERNAME"
  echo "User $USERNAME created and password set to expire"
fi


chmod 700 /home/"$USERNAME"

sudo chmod 750 /opt/raman-devops/scripts/create_user.sh
sudo chown root:devopsgrp /opt/raman-devops/scripts/create_user.sh


