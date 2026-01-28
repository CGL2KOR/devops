#!/bin/bash

REPORT_DIR="/opt/raman-devops/artifacts"
TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")
REPORT_FILE="$REPORT_DIR/cpu_report_$TIMESTAMP.txt"


if [[ ! -w "$REPORT_DIR" ]]; then
  echo "Cannot write to $REPORT_DIR"
  exit 1
fi


echo "CPU Usage Report - $TIMESTAMP" > "$REPORT_FILE"
echo "--------------------------------" >> "$REPORT_FILE"

ps -eo pid,comm,%cpu --sort=-%cpu | head -n 6 >> "$REPORT_FILE"


chmod 640 "$REPORT_FILE"
chown :devopsgrp "$REPORT_FILE"

