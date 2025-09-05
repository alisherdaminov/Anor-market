Anor Market (Uzum Market–style)
A production‑grade e‑commerce marketplace built with Spring Boot, inspired by Uzum Market, implementing full business workflows: catalog, media, cart, order management, promotions, reviews, admin, observability. Designed following Domain‑Driven Design (DDD), SOLID principles, and ready for enterprise scaling.

Tech stack: Spring Boot 3 • Java 17 • PostgreSQL • Spring data • AWS E2 • Spring Security (JWT) • MapStruct • Lombok • Swagger • GitHub Actions CI/CD.

Features
Customer‑facing
Authentication & User Profiles: phone/email login, JWT tokens, roles, profile management.
Catalog & Search: hierarchical categories, filters, full‑text search with Elasticsearch.
Product Media: high‑resolution images.
Cart & Checkout: persistent carts, promo codes, delivery selection, payment intents.
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

![Image](https://github.com/user-attachments/assets/05b04fcc-dfa8-4c2a-8120-83f3a5feb411)

![Image](https://github.com/user-attachments/assets/24f3ccc8-badf-412d-80f9-608e13911892)

![Image](https://github.com/user-attachments/assets/4af4eef1-ccce-4eda-bf81-76815fd56cba)

![Image](https://github.com/user-attachments/assets/774ef769-9727-4968-b180-e940dd263f07)

![Image](https://github.com/user-attachments/assets/1dcd331b-5739-4dfb-a1e2-179f9ba90425)

![Image](https://github.com/user-attachments/assets/aad6affa-7d77-4544-bf93-b902821479be)

![Image](https://github.com/user-attachments/assets/a2492a60-757f-4c8b-bc55-27ddbc24ab53)
