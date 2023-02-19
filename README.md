# NarutoFan 
 Android app for Boruto: Naruto Next Generations anime. It's a simple app that shows your favorite hero and his/her skills.

App respects its Base (Fragment , Activity , ViewMode , UseCase)

# Screenshots
 
<img src="https://user-images.githubusercontent.com/62269304/219944612-807563fd-d3eb-4d59-afd2-784edf275e5d.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944621-fd17dfd1-0ef5-433e-a8cb-a7410798f252.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944625-9f4acbe7-71d0-40ea-b664-1b07de24fae5.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944626-d94b0f06-f4a9-44b6-a03f-3cebd200bef9.jpg" width="200">&nbsp; 

<img src="https://user-images.githubusercontent.com/62269304/219944627-9081e4dc-9ae0-4e64-8543-a50d9c26ab71.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944629-785f1ae9-d730-42f4-bf8a-12bca70ae404.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944632-3d59d50a-8a86-4ed5-8ed9-62207f1195c6.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944633-ab1eabed-73a3-440e-a6e4-bb4e0022f723.jpg" width="200">&nbsp; 


<img src="https://user-images.githubusercontent.com/62269304/219944640-be75ec0c-a64d-4982-ac96-ca8869efb7cd.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944643-44c593b2-a99c-4a00-82c1-208c719498df.jpg" width="200">&nbsp; 
<img src="https://user-images.githubusercontent.com/62269304/219944647-d549ec37-1b76-4f6f-9d49-5b80294a175a.jpg" width="200">&nbsp; 


## Architecture

Clean Architecture with MVI


<img src="https://user-images.githubusercontent.com/62269304/219953027-99181bcb-3520-4c21-9408-5ad0b3c71731.png" width="800">&nbsp; 

## Built With

* [Kotlin](https://kotlinlang.org) - As a programming language.
* [Coroutines](https://developer.android.com/kotlin/coroutines) - For multithreading while handling requests to the server and local database.
* [Flow](https://developer.android.com/kotlin/flow) - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - To handle app navigation.
* [Sharedflow & StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) -To fire data
* [MVI Architecture](https://medium.com/swlh/mvi-architecture-with-android-fcde123e3c4a) - With no clear state management, view rendering along with the business logic can get a little bit messy as the application grows or adding functionalities or a feature that was not planned beforehand, and let’s be honest that can happen often, it’s rare to have all the features clearly and fully defined from the start of the project specifications, the more the app codebase is scalable the more it’s flexible to embrace new ideas and updates.
* [Model-View-ViewModel(MVVM)](https://developer.android.com/topic/architecture) - Offers an implementation of observer design pattern.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notifies views of any database changes in an observer way.
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) -Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
* [Glide](https://github.com/bumptech/glide) - It is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - It is arguably the most used Dependency Injection, or DI, framework for Android. Many Android projects use Dagger to simplify building and providing dependencies across the app. It gives you the ability to create specific scopes, modules, and components, where each forms a piece of a puzzle: The dependency graph.
* [Clean Architecture](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started) - Applying Clean Architecture , Solid Principles and use cases  to build a robust, maintainable, and testable
