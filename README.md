# Duplicate-files-finder
---
* This is a program which reads all images in a given directory and finds if any of the images are repeating more than once.
* It also reads images in sub-directories iteratively and finds if any duplicate images are present in any other sub-directory.

### Solution Approach
---
* **If two images are same, they would be same pixel by pixel. So the solution is to read each pixel of an image and creates hash (SHA-256) from it.** 
* If any other image has the same hash, then the images are same.
* The hash is stored in an **in-memory map having key=hash and value=A list of image paths having the same hash.** 
* Having hash instead of entire image in the memory, reduces memory load and hence a large number of images can be read by the program. 
* This program can also find more than once repetition of an image.  
* **In case there are millions of images to be read, a database can be used to store the hash values and keep a track of all the images that have been already read.** 

**Supported image formats:**
1. jpg
2. jpeg
3. png 
4. gif
5. tiff
 
### Pre-requisites
---
1. Install Java 11
2. gradle 6.3 

### Build And Run Tests
---
At project root execute following command: ```gradle clean build```

### Run Application using Gradle
---
1. Add the absolute directory path to search images in as an argument in the run task of build.gradle file.

2. At project root execute following command:  ```gradle clean build -x test run```

3. The program outputs the set of image paths in the logs that are duplicate.