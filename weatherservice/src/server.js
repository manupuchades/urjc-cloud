const grpc = require('grpc');
const WeatherService = require('./interface');
const weatherServiceImpl = require('./weatherService');

const server = new grpc.Server();

server.addService(WeatherService.service, weatherServiceImpl);

server.bind('${process.env.WEATHERSERVICE_HOST}:${process.env.WEATHERSERVICE_PORT}', grpc.ServerCredentials.createInsecure());

console.log('gRPC server running at ${process.env.WEATHERSERVICE_HOST}:${process.env.WEATHERSERVICE_PORT}');

server.start();