# spaceship-alex

Your task is to prototype a payload planning system for a space mission.
A lot of equipment needs to be transported in the spaceship for this mission, but, as you can imagine, there are severe weight constraints, so it is only possible to take a certain amount of the most important equipment. This is where your planning system comes in: your system will enable mission planners to prioritize and plan the equipment to be taken within mission weight limits.

- Every Item has a name, description, and weight.

## Architecture

We will be using the Controllers > Service > Repository pattern:

### Presentation Layer: Controller (@Controller)

Merely responsible to presenting a business function (provided in Application Service Layer). Hence mostly delegation to App Service, doing data massaging and presentation-related logic.

### Application Service Layer: Application Service (@Service)

High level speaking, it represents business use cases. Hence transaction boundary is set on the methods (as each method is a use-case, hence a unit of work). As I am against use of anemic model, real business logic should be in the domain model layer. This layer mostly make use of domain layer to compose of a meaningful use case. Mostly data transformations (depending on your design), and delegations to domain layer artifacts.

### Domain Layer: Model, Domain Service (@Service), Repository (@Repository) etc

Business logic are in domain models, or Domain Services. Repository are the artifacts to retrieve domain models.

## Steps

- [ ] Step 1: Create a new project and a package called `models`
- [ ] Step 2: Create a class for the entity `Item` within the package `model`. Define the types of the attributes, constructor and getters/setters.
- [ ] Step 3: Create a package called `repositories`
- [ ] Step 4: Create an interface called `ItemRepository`
- [ ] Step 5: Create a package called `controllers`
- [ ] Step 6: Create a class called `ItemController`. In this file you will need to define the endpoints that will be needed to create, read, update and delete items. (CRUD).
