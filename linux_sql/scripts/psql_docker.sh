#!/bin/bash

cmd=$1
db_username=$2
db_password=$3

# starting docker or showing status
sudo systemctl status docker || systemctl start docker

docker container inspect jrvs-psql
container_status=$? # grabs the output from the line above

# Create the start|stop|create operations
case $cmd in
    create)
	if [ $container_status == 0]; then
            echo 'Container already exists'
	    exit 1
	fi

	if [ $# -ne 3]; then
	    echo 'Create requires username and password'
	    exit 1
	fi

	docker volume create pgdata
	docker run --name jrvs_psql -e POSTGRES_USER=$db_username -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	exit $?
    ;;

    start|stop)
        if [$container_status ...
        ...

        docker container $cmd jrvs_psql
        exit $? 
    ;;

    *)
        echo "Illegal command"
	echo 'Commands: start|stop|create'
	exit 1
    ;;
esac
