Instruction to build and run Web Crawler Service
------------------------------------------------

1. client_dockerfile_v1.build
    This Dockerfile creates an image that provides a Java 11 runtime environment for a web crawler server application, installs necessary tools for editing and network analysis, sets up the working directory, exposes the necessary port, copies the application and a utility script into the container, and specifies how to start the application.

2. build_client.sh
    This script used for building a Docker image from a Dockerfile.

3. run_client.sh
    This script used to run a Docker container from an image. C

4. Jar file which contains all the required binaries. 

5. maven_build.sh
    This file is used to build jar file. 

6. test_client.sh 
    Sample Client usage is included in this file.  Client is built like linux Command. The following command will list the available options. 

    java -jar .\web-crawler-client-v1.jar -h
   Usage: wc_client [-aiz] [-d=<depth>] [-p=<port>] [-s=<host>] [-u=<url>]
  -a, --is-pretty-print   whether crawling to consider current domain of the
                            URL only
  -d, --depth=<depth>     depth of the URL crawl
  -i, --is-domain-only    whether crawling to consider current domain of the
                            URL only
  -p, --port=<port>       Server Port
  -s, --server=<host>     Server Host
  -u, --url=<url>         URL to be crawled.
  -z, --sleep-mode        dry run

