# Soft-3d-toy:

This is a soft 3d engine written in Java. It is called 'soft', which means that it runs on the PU, 
which also means that it's much slower than engines that take advantage of the GPU.

The project was made to learn about Java programming (this is in fact my first larger
Java project) as well as for learning about 3d programming techniques such as:

  - **computational matrix multiplication & arithmetics**
  - **3d projection arithmetics**
  - **object triangulation techniques**
  - **triangle rasterization**
  - **texture mapping to surfaces**
  - **surface normal vector based shading**
  - **depth buffering techniques (I am using the combination of painter's algorithm and backface culling)**

The repository has the following structure:

```
├ resources/
  ⌊ SELMECZI-GABRIELLA.jpg
⌊ src/
  ├ Camera.java
  ├ Engine.java
  ├ Matrix3X3.java
  ├ Mesh.java
  ├ MultipliableByMatrix.java
  ├ MultipliableByvectpr.java
  ├ Pair.java
  ├ ProjectedPoint.java
  ├ ProjectionMatrix.java
  ├ Test.java
  ├ Textrure.java
  ├ TranslationMatrix.java
  ├ Triangle.java
  ├ Triple.java
  ├ Vec2.java
  ├ Vec3.java
  ├ XaxisRotationMatrix.java
  ⌊ YaxisRotationMatrix.java
```

The [src/Engine.java](https://github.com/habospace/soft-3d-toy/blob/master/src/Engine.java) file contains the main logic of the 3d engine such as the 3d -> 2d projections
and drawing logics. The rest of the modules contain utility classes that represent entities 
such as triangles, points, meshes, textures (and so on...) used in the [src/Engine.java](https://github.com/habospace/soft-3d-toy/blob/master/src/Engine.java) file.

### You can check out the engine in operation here:

[![soft 3D engine test](https://i.ytimg.com/vi/95vz5KIjtyE/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAJ13DWIpRWuEi44IFfbvJwbCKIzA)](https://www.youtube.com/watch?v=95vz5KIjtyE "soft 3D engine test")

  
