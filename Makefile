MVNW = ./mvnw
ifeq ($(OS),Windows_NT)
    MVNW = mvnw.cmd
endif

run: build
	$(MVNW) spring-boot:run

build:
	$(MVNW) clean install
