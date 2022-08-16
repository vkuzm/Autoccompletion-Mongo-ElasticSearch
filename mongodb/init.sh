#!/bin/bash

mongo="${MONGODB_HOST:-localhost}"
mongoport="${MONGODB_PORT:-27017}"
mongouser="${MONGO_INITDB_ROOT_USERNAME:-}"
mongopass="${MONGO_INITDB_ROOT_PASSWORD:-}"
elasticsearch="${ELASTICSEARCH_HOST:-elasticsearch}"
elasticport="${ELASTICSEARCH_PORT:-9200}"

echo "**********************************************"
echo "Waiting for MongoDB startup.."

mongod --replSet development --bind_ip 0.0.0.0 &
sleep 5

mongo <<EOF
  rs.initiate();
EOF

echo "**********************************************"
echo "Waiting for mongo-connector startup.."
sleep 5

mongo-connector -m ${mongo}:${mongoport} -t ${elasticsearch}:${elasticport} -d elastic2_doc_manager