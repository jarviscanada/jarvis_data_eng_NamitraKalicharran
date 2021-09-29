#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
    echo "Invalid number of Arguments"
    exit 1
fi

# get hardware info
lscpu_out=$(lscpu)
hostname=$(hostname -f)

# grab each value for our host_info table
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk --field-separator ':' '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "CPU MHz:" | awk '{print $3}' | xargs)
L2_cache=$(echo "$lscpu_out" | egrep "L2 cache:" | awk '{print $3}' | grep -o '[0-9]\+' | xargs)
total_mem=$(grep "^MemTotal" /proc/meminfo | awk '{print $2}')
timestamp=$(date -u +"%Y-%m-%d %H:%M:%S")

# Insert data into our host_info psql table
insert_stmt="INSERT INTO host_info (
					hostname,
					cpu_number,
					cpu_architecture,
					cpu_model,
					cpu_mhz,
					L2_cache,
					total_mem,
					timestamp
				    )
			VALUES 	    (
					'$hostname',
					'$cpu_number',
					'$cpu_architecture',
					'$cpu_model',
					'$cpu_mhz',
					'$L2_cache',
					'$total_mem',
					'$timestamp');"

# Inject data into psql database
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
