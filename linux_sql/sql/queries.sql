-- Show all node's harware info
SELECT cpu_number, id, total_mem
FROM host_info
GROUP BY cpu_number
ORDER BY cpu_number ASC, total_mem DESC;

-- Find Average Memory used over 5 minutes
-- We will return entire table so that we can draw charts
SELECT host_usage.host_id,
	host_info.hostname,
	host_usage.timestamp,
	-- Since data is gathered every minute we just need to take the average of the last 5 rows
	AVG(100 * (host_info.total_mem - host_usage.memory_free) / host_info.total_mem) 
		OVER(ORDER BY host_usage.timestamp BETWEEN 4 PRECEDING AND CURRENT ROW) 
		as avg_used_mem_percentage
FROM host_info, host_usage
WHERE host_usage.host_id = host_info.id
ORDER BY host_usage.timestamp ASC

-- Detecting host failures
SELECT 
