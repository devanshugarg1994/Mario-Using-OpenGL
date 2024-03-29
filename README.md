# Mario-Using-OpenGL
Creating Mario Game with Engine Development using OpenGL and Java 



[Basic SetUp](../../tree/3dd31208d9d236acccda3a86137a5a0f61f3eeec)</br>
Set up the project and created GLFW window


[Delta Time and Basic Scene Manager](../../tree/2af5f87b011119a967edb8dc54ce9008630ae12f)</br>
Added Time class which give as the time difference from now to the application started. Also set up a Scene and add ome functionality to change between 2
</br>
</br>
[ Draw a Square Using OpenGL](../../tree/234ac52019dc52a873c14358eae880d64cfa698a)</br>
Created vab, vbo, ebo and render a square on the widow screen.
</br>
</br>
[Abstracting Shader string to a file and parsing it from file](../../tree/d7d3181fc345353d3c61cc54ff06bc2c5d33ff83)</br>
Created a Shader Class where we read and parse the `GLSL` code and break it down to vertex and fragment shader.We are compiling and Linking the shader program as well.
</br>
</br>
[Added Camera class which is using orthographic projection](../../tree/7a10a4e85091da762c11ed497632337b5f618c2c)</br>
Added a camera class here we are setting the projection as `orthographic` view, using `lookAt` function.
</br>
</br>
[Added More Uniform type and trying adding noise in Shaders](../../tree/3ebe7956d3f16905a3efdfa38b58fdf5cffde746)</br>
Added for uniform type and play around shader GLSL code.
</br>
</br>
[Added Texture](../../tree/c5d19750e18ca96f91291deabb0d6770e325b954)</br>
Added Texture class which take texture path as parameter while creating instance and handling setting other parameter.</br>
</br></br>
[Component Pattern SetUP](../../tree/b2de7ecf1e28adf0fceab1c6c174bd90e67a54e4)</br>
As we are working in Java it is not possible us to implement Data Oriented Pattern or ECS. Hence, we are going to use Component Pattern
</br></br>
[Transform Class Created](../../tree/b9542586ced40cb219a4c1c2b8d6710e51dd2d51)</br>
Transform class is responsible to position and scale value og gameObject.
</br></br>
[Batch Renderer](../../tree/d645b27d03da5ab0d4f1cdf282990cd02e8a5944)</br>
Created a 2D batched renderer, that is able to render thousands of sprites at 60 FPS.The game objects transform to create a set of
vertices for every single quad inside the batch renderer, and then upload that vertex buffer to the GPU every frame.</br>
Create a Renderer class that finds a RenderBatch that has room for a quad, or creates a new RenderBatch if there is none, and then adds the quad to that RenderBatch. 
The renderer will also sort the batches and render them to the screen.</br></br>
[Added AssetsPool class for resource management](../../tree/b593a85b64d8d54ede8e34f261d17dd0e74fddf1)</br>
We have created a class that handles all the resources (textures, shaders, sounds, sprite-sheets, etc.) and supply a reference to our 
game on demand, while only keeping one object in memory for each resource.</br></br>

[Texture Batch Rendering](../../tree/01abd11b64af8367055a89099f5ccf0055a0c02f)</br>
We  add texture batching to our current batched renderer. This allows us to bind up to 16 different textures 
per draw call, which means that we can batch together a lot more quads with different textures for each quad.
</br></br>

[Created SpriteSheet Class](../../tree/fe5f9b8b8914753d7ecd55ee57b6a2eb1c029e15)</br>
We have sample sub-images from a texture by changing the texture coordinates.
Actually implement this by creating a class that encapsulates this behavior in a spriteSheet in class called `SpriteSheet`. This spriteSheet is able to be queried for
a particular sprite by index, which makes it very convenient to use when grabbing multiple sprites from a single image.
</br></br>

[Added Dirty Flags](../../tree/4b56eb1fbcf4552385b151a626ecf28b89a4a5e7)</br>
Setup a dirty flag system with our render batched.
This allows us to only re-buffer data that has changed, which should save some CPU time while rendering our batches.
This also allows us to control how the data gets moved around everytime a value that we have interest in changes.
</br></br>

[Alpha Blending and zIndex](../../tree/7a6358183fbe8785fef07e68106e08326edfcb6b)</br>
We have enabled Alpha blending using  simple mathematical function.
Then we have implemented a z-indexing system for our batched renderer. This allows us to add sprites in a layered manner
like photoshop to our game.
We now have sufficient means to create layered 2D games that use the alpha channel for transparency.
</br></br>

[Imgui Setup and Integration with our Scene Architecture](../../tree/04dd8e228f3940d26d40f998aa637da4fb62d15e)</br> 
Setup ImGui(Immediate mode Gui). After that we have also Integrated our game Scene with ImGui.
</br></br>

[Serialization And Deserialization](../../tree/be74f5fe20923f30f75bd76f0d0b3ec358daaac3)</br>
Integrated GSON in our game. This library serializes objects into a Json format, which can then be deserialized with the same library.
It uses reflection to do this, which means that the programmer does not have to write any serialization code at all. TO avoid Circular 
Dependency error while deserializing json object we have extended and created Serialization and Deserialization for Components and 
GameObject class.
</br></br>

[Exposing Variables to the Level Editor](../../tree/8d837538c022c8e1f0dd0ca0dd60b634d363f101)</br>
Here we have expose public and private member variables of a class to
our level editor. This way we do not have to build a custom ImGui window for every variable
if we do not want too, but we still can do that if we would like to. We will use Java's
reflection library to find and expose these variables, and then we will use 
the Gson to save and load these values, making for a mostly complete level editor.
</br></br>

[Converting Screen Coords to World Coords](../../tree/b259c026f28c2daac0736b73adb17079b035e7df)</br>
Converted screen coordinates to world coordinates using our orthographic projection.
Created a dynamically resized window in ImGui, with image buttons that come from our spriteSheets.</br></br>

[Created Drag and Drop for Level Editor and Uuid System for game Object and components](../../tree/9e661db54ba03f89d17096c7e0a5689bad09d119)</br>
We have created a  `MouseControl` class which will handle the mouse positioning once a Tile is selected and will be set once we click again.
Also created  uuid system, so we can store references of other object while serializing and Deserializing.