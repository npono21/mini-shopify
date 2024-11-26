# Native Linux Metric Stack

## Procedure: Executing Actuator, Prometheus, and Grafana Natively on Linux
The below procedures describe how to install, configure, and execute Actuator, Prometheus, and Grafana natively (ie. non-containerized) on a Linux system. 

### Procedure: Configuring and Executing Spring Boot Web App and Actuator
1. Enable Actuator and Prometheus Dependencies in Maven's `pom.xml`, under the `<dependencies>` parent tag:
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>
```
1. Configure Actuator to collect desired metrics, and to expose desired endpoints. This can be done in either a plantext file or in a YAML file:
    1. `src/main/resources/application.properties`
    1. `src/main/resources/application.yml`
For example, our project uses the following `application.properties` file:
```
management.endpoint.prometheus.enabled=true
management.endpoints.web.exposure.include=*
management.metrics.enable.http.server.requests=true
```
1. Deploy your Java Spring Boot Web App. This will also run Actuator as a sidecar to your application, and will aggregate and expose metrics from your application via the configured endpoints, makng them accessible to other processes on your host network. Impoortantly for this demonstration, we expose the `/actuator/prometheus` endpoint for use by the Prometheus process.
1. Verify your Spring Boot Web App is available at: 
    1. `http://localhost:8080/`
1. Verify your web app's `/actuator/prometheus` endpoint is available at: 
    1. `http://localhost:8080/actuator/prometheus`

### Procedure: Configuring and Executing Prometheus
1. Install Prometheus to your system. For example, on a Debian-based distro:
```bash
sudo apt install prometheus
```
1. Configure Prometheus to scrape metrics from the exposed `/actuator/prometheus` endpoint by supplying a configuration file. The default configuration file is found at `/etc/prometheus/prometheus.yml`, although this can be overridden with any other file at runtime by arguing it with the `--config.file` switch.
For example, our project uses the following Prometheus configuration file.
```yaml
# Global configurations
global:
  scrape_interval: 15s # Scrape every 15 seconds (default: 1 minute)
  evaluation_interval: 15s # Evaluate rules every 15 seconds (default: 1 minute)

# Alertmanager configuration (commented out since no Alertmanager is set up)
alerting:
  alertmanagers:
  - static_configs:
    - targets:
      # Uncomment and set alertmanager target if needed
      # - alertmanager:9093

# Rule files for alerts (optional, not needed for basic setup)
rule_files:
# - "alerts.yml"

# Scrape configurations
scrape_configs:
  # Scraping Prometheus itself
  - job_name: "prometheus"
    static_configs:
      - targets: ["localhost:9090"]

  # Scraping Spring Boot application
  - job_name: "spring-boot-app"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["localhost:8080"]
```
1. Run Prometheus with root privileges, arguing the desired configuration file:
```bash
sudo prometheus --config.file="path/to/config.yaml"
```
1. Verify Prometheus is reachable at one of the two following endpoints (depending on the version of Prometheus): 
    1. `http://localhost:9090/targets/`
    1. `http://localhost:9090/classic/targets/`
1. Verify Prometheus can see you `/actuator/prometheus` endpoint, and that your web application is listed as a Target.

### Procedure: Configuring and Executing Grafana
1. Install Prometheus to your system. For example, on a Debian-based distro:
```bash

# Install dependencies
sudo apt install -y apt-transport-https software-properties-common wget

# Add Grafana's GPG key to your keyring
sudo mkdir -p /etc/apt/keyrings
wget -q -O - https://apt.grafana.com/gpg.key | gpg --dearmor | sudo tee /etc/apt/keyrings/grafana.gpg > /dev/null

# Update your APT source list to query Grafana repository
echo "deb [signed-by=/etc/apt/keyrings/grafana.gpg] https://apt.grafana.com stable main" | sudo tee -a /etc/apt/sources.list.d/grafana.list

# Update your package manifest
sudo apt update

# Install Grafana package
sudo apt install grafana
```
1. Run the Grafana Server application by starting the system service:
```bash
sudo systemctl start grafana-server.service
```
1. Verify Grafana is reachable at: 
    1. `http://localhost:3000/`
1. Login to Grafana with deafult credentials of `admin/admin`.
1. Add a Dashboard to Grafana by clicking the __New Dashboard__ button.
1. Add a Visualization to the dashboard by clicking the __New Visualization__ button.
1. Add a Data Source to the visualizatoin by specifiying __Prometheus__ as your data source, and ensuring it has the Prometheus endpoint as its URL (ie. `http://localhost:9090/`)
1. Add a Metric to the visualization by typing into the __Metric__ box and selecting the desired metric (eg. `http_server_requests_active_seconds_count`).
1. Run the query by clicking the __Run Queries__ button.

### Procedure: Verifying the Entire Stack
1. Trigger endpoints on your web application by clicking buttons, completing forms, etc.
1. Verify your Grafana Dashboard is displaying the metrics associated with your endpoints in its visualization.

### Procedure: Clean Up
1. Terminate your Java Spring Boot Web App process and Actuator process.
1. Terminate your Prometheus process.
1. Stop the Grafana Server with:
```bash
sudo systemctl stop grafana-server.service
```
