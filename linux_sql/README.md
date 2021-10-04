# Linux Cluster Monitoring Agent

# Introduction
This is a monitoring tool for the Jarvis Linux Cluster Administration (LCA) team. This tool will be able to store and track the hardware specifications of each node as well as monitor their resources. The tech powering this tool is bash scripts which will access a postgres database, while the entire project will be containerized using Docker.

# Quick start
- Ensure Docker and Postgresql are installed on your machine
- Start a psql instance using `psql_docker.sh create` on host node
- Run `host_info.sh` on each node
- Schedule crontab to execute `host_usage.sh` every minute for each node

# Implementation
This tool was implemented using bash scripts and the database was containerized in Docker

## Architecture

## Scripts
```
linux_sql
├── assets
├── README.md
├── scripts
│   ├── host_info.sh
│   ├── host_usage.sh
│   └── psql_docker.sh
└── sql
    ├── ddl.sql
    └── queries.sql
```
## Database Modelling

### Host Info Table

| id | Postgres ID |
| hostname | Name of node |
| cpu\_number | Node CPU cores |
| cpu\_architecture | Node CPU architecture (e.g. x86) |
| cpu\_model | Node CPU model |
| cpu\_mhz | Node CPU clock speed |
| L2\_cache | Node CPU L2 cache |
| total\_mem | Node total RAM |
| timestamp | Date hardware info captured |

### Host Usage Table

| timestamp | host\_id | memory\_free | cpu\_idle | cpu\_kernel | disk\_io | disk\_available |
| --------- | -------- | ------------ | --------- | ----------- | -------- | --------------- |

# Test

# Deployment

# Improvements
