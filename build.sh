cd frontend
npm ci
npm run build
rm -rf ../backend/src/main/resources/static
mv build ../backend/src/main/resources/static
cd ../backend
./mvnw clean package
cd ..
docker build -t quiz-frontend-docker .