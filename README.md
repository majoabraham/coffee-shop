Two Java microservices for a fictive coffee shop: 
- Orders service
- Barista service

ORDERS SERVICE

It maintains the customer orders for a coffee. 

1. An order contains information about 
   - type of coffee drink -> long black, latte, cappuccino, espresso
   - size of the ordered coffee -> small, medium, large
   - milk type (if applicable) -> cow milk / soy milk / almond milk
   - on-site / takeaway
   - price
   - order number
2. The orders are persisted using JPA.
3. The service implements an external REST interface for
   - adding an order
   - canceling an order
   - listing of pending orders with their current status
4. An order, which is already in preparation, cannot be cancelled. 
5. The completed orders are forwarded to Barista service for processing.
6. The orders, finalized by Barista service, are removed from the order list.
	
BARISTA SERVICE

This service is responsible for order processing.  
The order in the Barista service goes through the following states: 

waiting -> in preparation -> finished -> picked up
	
The order preparation state is persisted.  
The finalized orders are removed from the persistence storage.

- The persistence is implemented with Hibernate and in-memory / embedded relational database H2
- The internal communication between the microservices are implemented in form of REST web services
