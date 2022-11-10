<script>
	import PortfolioPiece from './PortfolioPiece.svelte';
	import TodoItem from '$lib/TodoItem.svelte'
	import ExternalLink from '$lib/ExternalLink.svelte';
</script>

<svelte:head>
	<title>Westrom.xyz - Portfolio</title>
</svelte:head>

<h1>Portfolio Pieces</h1>

<TodoItem link="https://github.com/wildwestrom/mysite/issues/20">
		Add some links to the repositories and working demos of my projects.
</TodoItem>

<ul>
	<PortfolioPiece name="This Website">
		<p>
			This project started as an experiment in repl-driven development, using ClojureScript and React components. I rewrote it with <ExternalLink link="https://kit.svelte.dev">SvelteKit</ExternalLink> so I could build my site with components but render it with simple HTML and CSS.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Death Calendar">
		Inspired by the <ExternalLink link="https://waitbutwhy.com/2014/05/life-weeks.html">waitbutwhy.com</ExternalLink> article, I decided to create a visual representation of how much time you have until your ultimate demise. It's designed to help you spend more time on the things that really matter to you.
	</PortfolioPiece>
	<PortfolioPiece name="Feedback Box Component">
		<p>
			A component designed to allow users to send feedback to an endpoint. It was designed to be easily deployable as a single <code>.js</code> file. Despite having no prior knowledge, I wrote this using the <ExternalLink link="https://github.com/tonsky/rum">Rum</ExternalLink> library.
		</p>
		<p>This was my first paid commission.</p>
	</PortfolioPiece>
	<PortfolioPiece name="TSDL">
		<p>
			There's this artist who makes some cool desktop wallpapers, so I decided to make a web-scraping tool to help me download all the images on her site. The earliest iteration of it was written in POSIX-compliant shell-script.
		</p>
		<p>
			Now it has a swing-based user interface (<ExternalLink link="https://github.com/clj-commons/seesaw">seesaw</ExternalLink>). This is also my first project where I use a logging framework (<ExternalLink link="https://github.com/BrunoBonacci/mulog">mulog</ExternalLink>) and make use of Clojure's concurrency model, <ExternalLink link="https://clojure.org/news/2013/06/28/clojure-clore-async-channels"><code>core.async</code></ExternalLink>.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Hexagram SVG Generator">
		The I-Ching is an ancient Chinese system of divination. It involves creating a figure of 6 lines, broken or unbroken, usually by casting coins or picking yarrow stalks. This demo generates an SVG graphic based on the hexagram number.</PortfolioPiece>
	<PortfolioPiece name="Uniorg Util">
		<p>
			This project was a workaround for this website when I couldn't run certain code due to an es-module and commonjs incompatibility. It converts <ExternalLink link="https://orgmode.org">Org documents</ExternalLink> to HTML and metadata.
		</p>
	</PortfolioPiece>
</ul>

<style>
</style>
