// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/books-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const id = event.pathParameters.id;

  try {
    const book = await dbManager.deleteBook(id);

    if (book) {
      return {
        statusCode: 200,
        body: JSON.stringify(book),
      };
    } else {
      return {
        statusCode: 404,
        body: { error: "Book not found" },
      };
    }
  } catch (err) {
    return {
      statusCode: 400,
      body: { error: "Invalid book id" },
    };
  }
};
