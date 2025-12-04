.PHONY: build deploy remove logs ps

build:
	@echo "Building Docker image..."
	@export $$(cat .env) > /dev/null 2>&1; docker build -t $${REG_URL}/$${PROJECT_NAME}:java-$${TAG} .

deploy:
	@echo "Deploying stack..."
	@export $$(cat .env) > /dev/null 2>&1; docker stack deploy -c docker-compose.yml fina

remove:
	@echo "Removing stack..."
	@docker stack rm fina

logs:
	@docker service logs -f fina_java-app

ps:
	@docker stack ps fina
