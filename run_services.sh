#!/bin/bash

echo "🚀 Building and packaging services (tests skipped)..."

# Define service directories
SERVICES=("notification-service" "order-service" "payment-service" "inventory-service")
BASE_DIR="C:/T-Projects/order-processing-system"

# Convert Windows path to Git Bash/Unix format
BASE_DIR=$(cygpath -u "$BASE_DIR")

# Loop through each service and run Maven commands
for SERVICE in "${SERVICES[@]}"; do
    echo "🔧 Building $SERVICE..."
    cd "$BASE_DIR/$SERVICE" || { echo "❌ Failed to enter directory: $SERVICE"; exit 1; }

    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        echo "❌ Build failed for $SERVICE!"
        exit 1
    fi
done

echo "✅ All services built successfully (tests skipped)!"
exit 0
