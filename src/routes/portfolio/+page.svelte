<script lang="ts">
	import PortfolioPiece from './PortfolioPiece.svelte';
	import PortfolioLink from './PortfolioLink.svelte';
	import ExternalLink from '$lib/ExternalLink.svelte';

	const NAME_FOR_REPOSITORY: string = 'Source Code';
</script>

<svelte:head>
	<title>Westrom.xyz - Portfolio</title>
</svelte:head>

<h1>Portfolio Pieces</h1>

<ul>
	<PortfolioPiece name="This Website">
		<ul class="portfolio-links">
			<PortfolioLink href="https://www.westrom.xyz/" target="_self" icon="globe"
				>Westrom.xyz</PortfolioLink
			>
			<PortfolioLink href="https://github.com/wildwestrom/mysite" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			This project started as an experiment in REPL-driven development using ClojureScript and React
			components. I rewrote it with <ExternalLink href="https://kit.svelte.dev"
				>SvelteKit</ExternalLink
			> so I could build my site with components but render it with simple HTML and CSS.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Hantracker">
		<ul class="portfolio-links">
			<PortfolioLink href="https://github.com/wildwestrom/hantracker" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			A tool for keeping track of how many Chinese characters you can read. Anki is too complicated
			for what I wanted it to do, and <ExternalLink
				href="https://github.com/fabd/kanji-koohii/pull/204"
				>this feature from Kanji Koohii</ExternalLink
			> got removed, so I decided to build my own. It doesn't actually test you, you just have to say
			"yes" or "no" to whether you recognize each character.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Death Calendar">
		<ul class="portfolio-links">
			<PortfolioLink href="https://github.com/wildwestrom/death-calendar" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		Inspired by the <ExternalLink href="https://waitbutwhy.com/2014/05/life-weeks.html"
			>waitbutwhy.com</ExternalLink
		> article, I decided to create a visual representation of how much time you have until your ultimate
		demise. It's designed to help you spend more time on the things that really matter to you.
	</PortfolioPiece>
	<PortfolioPiece name="Factorio Blueprint String Creator">
		<ul class="portfolio-links">
			<PortfolioLink href="https://github.com/wildwestrom/blueprint-string-creator" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			A command line tool for turning text into Factorio blueprint strings. This is yet another rust
			rewrite from Clojure.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Feedback Box Component">
		<ul class="portfolio-links">
			<PortfolioLink href="https://github.com/wildwestrom/feedback-box-component" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			A component designed that allows a user to send feedback. It was designed to be easily
			deployable as a single <code>.js</code> file. Despite having no prior knowledge, I wrote this
			using the <ExternalLink href="https://github.com/tonsky/rum">Rum</ExternalLink> library.
		</p>
		<p>This was my first paid commission.</p>
	</PortfolioPiece>
	<PortfolioPiece name="Hexagram SVG Generator">
		<ul class="portfolio-links">
			<PortfolioLink href="https://hexagram-svg.netlify.app/" icon="globe">Demo</PortfolioLink>
			<PortfolioLink href="https://github.com/wildwestrom/hexagram-svg-generator" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		The I-Ching is an ancient Chinese system of divination. It involves creating a figure of 6 lines,
		broken or unbroken, usually by casting coins or picking yarrow stalks. This demo generates an SVG
		graphic based on the hexagram number.</PortfolioPiece
	>
	<PortfolioPiece name="Uniorg Util">
		<ul class="portfolio-links">
			<PortfolioLink href="https://github.com/wildwestrom/uniorg-util" icon="git"
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			This project was a workaround for this website when I couldn't run certain code due to an
			es-module and commonjs incompatibility. It converts <ExternalLink href="https://orgmode.org"
				>Org documents</ExternalLink
			> to HTML and metadata.
		</p>
	</PortfolioPiece>
</ul>

<style>
	.portfolio-links {
		margin: 0.5em 0;
	}
</style>
