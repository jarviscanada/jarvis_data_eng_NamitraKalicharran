-- First Business Question

SELECT cpu_number, id, total_mem
FROM host_info
GROUP BY cpu_number
ORDER BY cpu_number ASC, total_mem DESC

-- Second Business Question

