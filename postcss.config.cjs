const nested = require('postcss-nested');
const autoprefixer = require('autoprefixer');

const config = {
	syntax: 'postcss-scss',
	plugins: [nested(), autoprefixer()]
};

module.exports = config;
