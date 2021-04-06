###Vertical slicing architecture
Code is organized around features (get ads, collect impressions, get stats) as opposed to commonly used layered architecture (clean architecture, 3 tier architecture) where code is organized around functions (controllers, services, DAO).

Package structure organized around features makes it easier to navigate and read and so reduce cognitive load (as you don't have to jump around different packages and keep them in mind). 

This allows better and safer refactoring because the developer is not afraid to break some other feature and removes the need to create redundant classes for a sake of being similar to other code features.

Another benefit as the system grows up is that it is much easier to extract some features to separate services/serverless functions because code is already colocated and has little dependencies.

The drawback of this architecture is to deal with code used between multiple features. Three common approaches are:
- duplicate logic for each feature (duplication is cheaper than the wrong abstraction)
- to move shared code to some package used by multiple features
- place code in a feature package that seems more "important"
Code also follows SOLID principles.  

###Key-value store
Redis was selected as a key-value store for keeping counters of ad requests and impressions for SDK versions and users.

Redis is open source, it has great documentation and community support, Spring Data Redis wrapper.

Also, it scales great as data is stored in memory and is regularly backed up on a disk.

This type of data store works fast for multiple concurrent updates as data is stored in memory and is regularly backed up on the disk.

For the analytics use case, I think this benefit outweighs the possibility of losing the data collected in memory for a short period of time and not stored on the disk.  Real-time analytics is a common use case for Redis.

###How to run
```
mvn clean package
docker-compose build
docker-compose run
```        