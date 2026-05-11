# Legacy-to-Cloud: SOAP to REST Migration
## Architecture Modernization via Strangler Fig Pattern

This repository serves as a comprehensive proof-of-concept for migrating legacy Java Enterprise applications to modern Spring Boot microservices. It demonstrates the **Strangler Fig Pattern**, allowing for a risk-mitigated, incremental transition while maintaining system uptime.

### 🏗 Architecture Overview

The project implements a multi-tier routing strategy using **Traefik** as a **Reverse Proxy** to orchestrate traffic between two distinct worlds:

1.  **The Monolith (Simulated):** A Spring Boot-based SOAP server. This simulates the behavior of a legacy EJB backend without the unnecessary infrastructure overhead of ancient application servers, allowing the focus to remain on **protocol migration** rather than legacy environment maintenance.
2.  **The Modern Stack:** A React/Angular frontend communicating with a Spring Boot (v4.0.x / Java 25) REST API.

### ♟️ The Migration Strategy (Strangler Fig)

Instead of a dangerous "Big Bang" rewrite, this repository demonstrates how to:
* Place a **Reverse Proxy (Traefik/NGINX)** at the edge of the infrastructure to act as a traffic router.
* Intercept specific URL paths (e.g., `/api/v1/users`) and route them to the new Spring Boot services.
* Maintain legacy routes pointing to the original SOAP-based application during the transition phase.
* Experience the **"RPC Illusion"** by consuming SOAP endpoints through generated JAX-WS clients.

### ⚙️ Tech Stack
* **Edge Routing:** Traefik (Reverse Proxy)
* **Modern Backend:** Spring Boot 4.0.6, Java 25 (utilizing Virtual Threads / Project Loom)
* **Modern Frontend:** React / Angular
* **Legacy Simulation:** Spring Boot + JAX-WS (Simulated SOAP Monolith)
* **Infrastructure:** Docker Compose (Hybrid environment simulation)

### 🚀 Getting Started
1. **Clone the repo:** `git clone ...`
2. **Build the modules:** `mvn clean install`
3. **Spin up the environment:** `docker-compose up -d`
4. **Access the Proxy:** Visit `http://localhost` to see the path-based routing in action.

---
*Created as a professional development resource for aiding organizations in complex cloud-native migrations and legacy system decommissioning.*