Instruction to build and run Web Crawler Service
------------------------------------------------

1. server_dockerfile_v1.build
    This Dockerfile creates an image that provides a Java 11 runtime environment for a web crawler server application, installs necessary tools for editing and network analysis, sets up the working directory, exposes the necessary port, copies the application and a utility script into the container, and specifies how to start the application.

2. build_sever.sh
    This script used for building a Docker image from a Dockerfile.

3. run_server.sh
    This script used to run a Docker container from an image.

4. Jar file which contains all the required binaries. 

5. maven_build.sh
    This file is used to build jar file. 
