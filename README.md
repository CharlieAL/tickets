## 📋 Summary

A web-based platform enabling users to create events, manage ticket sales, and generate QR-coded tickets for attendees,
streamlining the event management and ticket distribution process.

---

## 📘 Definitions

### Event

A planned gathering or occasion with a specific date, time, and venue that requires ticketing for attendance management.

### Ticket

A digital or physical document that grants the holder access to an event, containing event details and a **unique QR
code** for validation.

### QR Code

A machine-readable code consisting of black and white squares, used to store ticket information and verify authenticity
at event entry.

---

## 👥 User Stories

### 1. Create Event

**As an** event organizer

**I want to** create and configure a new event with details like date, venue, and ticket types

**So that** I can start selling tickets to attendees

### ✅ Acceptance Criteria:

- Organizer can input event name, date, time, and venue
- Organizer can set multiple ticket types with different prices
- Organizer can specify total available tickets per type
- Event appears on the platform after creation

---

### 2. Purchase Event Ticket

**As an** event goer

**I want to** purchase the correct ticket for an event

**So that** I can attend and experience the event

### ✅ Acceptance Criteria:

- Event goer can search for events
- Event goer can browse and select different ticket types available for each event
- Event goer can purchase their chosen ticket type

### 3. Manage Ticket Sales

**As an** event organizer

**I want to** monitor and manage ticket sales

**So that** I can track revenue and attendance

### ✅ Acceptance Criteria:

- Dashboard displays sales metrics
- Organizer can view purchaser details
- System prevents overselling of tickets
- Sales automatically stop at specified end date

---

### 4. Validate Tickets

**As an** event staff member

**I want to** scan attendee QR codes at entry

**So that** I can verify ticket authenticity

### ✅ Acceptance Criteria:

- Staff can scan QR codes using mobile device
- System displays ticket validity status instantly
- System prevents duplicate ticket use
- Staff can manually input ticket numbers if QR scan fails
