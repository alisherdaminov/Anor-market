Anor Market (Uzum Market–style)
A production‑grade e‑commerce marketplace built with Spring Boot, inspired by Uzum Market, implementing full business workflows: catalog, media, cart, order management, promotions, reviews, admin, observability. Designed following Domain‑Driven Design (DDD), SOLID principles, and ready for enterprise scaling.

Tech stack: Spring Boot 3 • Java 17 • PostgreSQL • Spring data • AWS E2 • Spring Security (JWT) • MapStruct • Lombok • Swagger • GitHub Actions CI/CD.

Features
Customer‑facing
Authentication & User Profiles: phone/email login, JWT tokens, roles, profile management.
Catalog & Search: hierarchical categories, filters, full‑text search with Elasticsearch.
Product Media: high‑resolution images, video support, thumbnails, S3/MinIO integration.
Cart & Checkout: persistent carts, promo codes, delivery selection, payment intents.
Order Lifecycle: tracking statuses, invoices, delivery partner integration.
Reviews & Ratings: verified reviews, aggregated ratings, abuse reporting.
Recommendations: personalized (based on history) & global top sellers.

Architecture
High‑level
[ Client Apps ] → [ API Gateway ] → [ Anor Market Backend ] → [ Postgres]
Modular monolith with DDD aggregates.
REST API, versioned, documented with OpenAPI.
Stateless services, JWT security.
Event‑driven flows for async processing.
Layers
Presentation: REST controllers, DTOs, request validation.
Application: service orchestrations, transaction boundaries, events.
Domain: aggregates, entities, value objects, domain services.
Infrastructure: persistence (JPA), cache, messaging, media storage.

