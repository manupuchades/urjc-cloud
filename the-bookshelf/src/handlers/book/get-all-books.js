// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/books-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const data = await dbManager.getAllBooks();

  return {
    statusCode: 200,
    body: JSON.stringify(data),
  };
};
