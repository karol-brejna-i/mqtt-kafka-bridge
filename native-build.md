This document was intended to be a guide for building native images for Quarkus app.

I had huge problems with that before (working on WSL2/Windows).

Now the following command worked perfectly fine (without the need to install GraalVM, etc.):
```
./mvnw clean package \
    -Pnative \
    -Dquarkus.native.container-build=true \
    -Dquarkus.container-image.build=true \
    -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11
```

So, no special instructions are required.

