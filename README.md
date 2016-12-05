Soft-3d-toy:

This is a Java based 3d engine. It is called 'soft', thus it runs on CPU, which also means
that it's generally much slower than if it was built for GPU.

The project was made for practicing Java programming (this is in fact my first larger-scale
Java project) as well as for learning the following 3d programming techniques:

  - matrix multipliction
  - 3d projection
  - triangulation
  - triangle rasterization
  - texture mapping
  - surface normal vector based shading
  - depth buffering techniques (though instead of 'Z' buffering, I finally ended up
                                using the combination of painter's algorithm and
                                backface culling)
  
The 3d engine can project any sufficiently specified 3d meshes onto a 2d surface. 
