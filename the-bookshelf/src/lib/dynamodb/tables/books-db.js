const {docClient} = require("../dynamo-client.js");
const uuid = require("uuid");

// Get the DynamoDB table name from environment variables
const booksTable = 'the-bookshelf-book';

const createBook = async (data) => {
  const params = {
    TableName: booksTable,
    Item: {
      id: uuid.v1(),
      title: data.title,
      author: data.author,
      summary: data.summary,
      publisher: data.publisher,
      publicationYear: data.publicationYear,
    },
  };
  const book = await docClient.put(params).promise();
  return book.Item;
};

const deleteBook = async (id) => {
  const params = {
    TableName: booksTable,
    Key: {
      id,
    },
    ReturnValues: "ALL_OLD",
  };

  const book = await docClient.delete(params).promise();
  return book.Item;
};

const getAllBooks = async () => {
  const params = {
    TableName: booksTable,
  };

  const books = await docClient.scan(params).promise();
  return books.Items;
};

const getBookById = async (id) => {
  const params = {
    TableName: booksTable,
    Key: {
      id,
    },
  };
  const book = await docClient.get(params).promise();
  return book.Item;
};

module.exports = {
  createBook,
  deleteBook,
  getAllBooks,
  getBookById,
};
