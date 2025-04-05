# 🧩 Order Processing System

This project contains a set of microservices designed to simulate a simple order processing system. The services
include:

- `order-service`
- `payment-service`
- `inventory-service`
- `notification-service`

It also includes a Cassandra database used for storing order history data.

---

## 🐳 Running the Microservices with Docker Compose

### 1. Prerequisites

Ensure you have the following installed:

- Java 17
- Maven
- Docker Desktop (with WSL 2 integration enabled)
- Git Bash or any terminal that supports Bash scripting

---

### 2. Building the Services

Before running the services, you need to build each one using Maven.

Use the provided script:

```bash
./run_services.sh
```

🛠 This will run mvn clean package -DskipTests for each service under the C:/T-Projects/order-processing-system
directory.

### 3. Starting Cassandra

Run the script below to start the Cassandra container and initialize the keyspace and table:

```bash
./start_and_init_cassandra.sh
```

This script does the following:

Starts Cassandra using Docker Compose

Waits until Cassandra is healthy

Creates the orders_keyspace keyspace

### 4. Running Cassandra database

#### 4.1. Starting Cassandra container with Docker Compose

```bash
docker-compose up -d cassandra
```

#### 4.2. Initializing Cassandra

```bash
docker exec -it cassandra cqlsh
```
#### 4.3. Applying schema

```bash

CREATE KEYSPACE IF NOT EXISTS orders_keyspace
WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

USE orders_keyspace;

CREATE TABLE IF NOT EXISTS order_history (
  orderid bigint PRIMARY KEY,
  customername text,
  product text,
  quantity int,
  paymentstatus text
);

USE orders_keyspace;

SELECT * FROM order_history;
```
