const dbManager = require("../../lib/dynamodb/tables/books-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const data = JSON.parse(event.body);

  try {
    const book = await dbManager.createBook(data);
    return buildResponse(201, data);
  } catch (err) {
    return {
      statusCode: 400,
      body: JSON.stringify(err),
    };
  }
};

function buildResponse(statusCode, body) {
  return {
    statusCode,
    body: JSON.stringify(body),
  };
}
