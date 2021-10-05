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
ORDER BY host_usage.timestamp ASC;

-- Detecting host failures
WITH grid AS (
   -- Get time intervals to compare
   SELECT start_time
        , lead(start_time, 1, 'infinity') OVER (ORDER BY start_time) AS end_time
   FROM  (
      -- Get our host_usage time series
      SELECT generate_series(min(timestamp), max(timestamp), interval '5 min') AS start_time
      FROM   host_usage
      ) sub
   )
-- Get the time of failure and number of records at that time
SELECT start_time, count(e.ts) AS events
FROM   grid       g
LEFT   JOIN event e ON e.ts >= g.start_time
                   AND e.ts <  g.end_time
WHERE events < 5
GROUP  BY start_time
ORDER  BY start_time; 
