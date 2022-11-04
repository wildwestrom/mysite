const nested = require('postcss-nested');
const autoprefixer = require('autoprefixer');

const config = {
	plugins: [nested(), autoprefixer()]
};

module.exports = config;
