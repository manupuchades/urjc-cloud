// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/users-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const data = await dbManager.getAllUsers();

  return {
    statusCode: 200,
    body: JSON.stringify(data),
  };
};
