FROM fedora:latest

RUN dnf -y install httpd && dnf clean all

ADD webserver.tar.gz /var/www/html/

ENTRYPOINT [ "/usr/sbin/httpd" ,"-D" ,"FOREGROUND"]

EXPOSE 80


