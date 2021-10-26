#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Check all parameters were passed
if [ $# -ne 5 ]; then
    echo "Invalid number of Arguments"
    exit 1
fi

# Save machine stats
vmstat_out=$(vmstat --unit M)
hostname=$(hostname -f)

# Pull usage data
memory_free=$(echo "$vmstat_out" | awk '{print $4}' | tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_out" | awk '{print $15}' | tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_out" | awk '{print $14}' | tail -n1 | xargs)
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available=$(df -BM | egrep '/dev/sda2' | awk '{print $4}' | grep -o "[0-9]\+" | xargs)
timestamp=$(date -u +"%Y-%m-%d %H:%M:%S")

# insert usage into PSQL database into host_usage table
insert_stmt="INSERT INTO host_usage (
					timestamp,
					host_id,
					memory_free,
					cpu_idle,
					cpu_kernel,
					disk_io,
					disk_available
				    )
		          SELECT
					'$timestamp',
					host_info.id,
					'$memory_free',
					'$cpu_idle',
					'$cpu_kernel',
					'$disk_io',
					'$disk_available'
			  FROM host_info WHERE host_info.hostname='$hostname';"

# save env var for psql command
export PGPASSWORD=$psql_password
# Insert data into our database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
