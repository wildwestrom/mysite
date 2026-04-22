<script lang="ts">
	import PortfolioPiece from './PortfolioPiece.svelte';
	import PortfolioLink from './PortfolioLink.svelte';
	import ExternalLink from '$lib/ExternalLink.svelte';

	const NAME_FOR_REPOSITORY: string = 'Source Code';

	let { data } = $props();
</script>

<svelte:head>
	<title>Westrom.xyz - Portfolio</title>
</svelte:head>

<h1>Portfolio Pieces</h1>

<ul>
	<PortfolioPiece name="This Website">
		<ul class="portfolio-links">
			{#snippet iconGlobe()}
				<svg inline-src="globe" width="1.5em" height="1.5em" />
			{/snippet}
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://www.westrom.xyz/" target="_self" icon={iconGlobe}
				>Westrom.xyz</PortfolioLink
			>
			<PortfolioLink href="https://github.com/wildwestrom/mysite" icon={iconGit}
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
	<PortfolioPiece name="Bare Metal RISC-V Bootstrap">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/stage0-riscv64-baremetal" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			Ported the stage0 bootstrap chain (hex0 → hex1 → hex2 → M0) from stage0-posix-riscv64 to
			bare-metal RISC-V 64-bit, replacing all POSIX syscalls with direct UART I/O, executing
			directly in memory. Each stage is validated with automated QEMU-based integration tests, and
			hex0 — the trust anchor of the entire chain — was manually audited byte-by-byte against the
			RISC-V ISA spec.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Node-Graph Editor/Compiler">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/node-compiler" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			A node-graph editor designed to output bytes via a data pipeline. A compiler is really just a
			machine that takes in bytes and outputs bytes right? Anyway, it supports logical and
			arithmetic operations, as well as concatenating and splitting byte streams. This is a
			companion project to the bootstrapping chain above. I think content-addressed, graphically
			represented code is the future.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Track Geometry Experiments">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			{#snippet iconGlobe()}
				<svg inline-src="globe" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/track-geometry-experiments" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
			<PortfolioLink
				href="https://wildwestrom.github.io/track-geometry-experiments/"
				icon={iconGlobe}>Demo</PortfolioLink
			>
		</ul>
		<p>
			I made an editor with the Bevy game engine with procedural terrain generation and a railway
			alignment editor. The railway alignments are splines composed of straight segments, circular
			arcs, and clothoid transitions. In the future I'd like to get vertical alignments working as
			well.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Proof of the Smoothstep Curve">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/smoothstep-curve-proof" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			A companion project to Track Geometry Experiments. I wanted to prove that you could design a
			perfect transition curve. Jerk is the 2nd derivative of angular velocity, but what if I could
			make a curve that's continuous on infinite higher derivatives? With this in mind, I sought to
			prove that there's a curve family that has exactly this property ({@html data.gInfinity} continuity)
			among others. I used Lean 4 to prove my intuition was correct and I wasn't just speculating by
			looking at Desmos graphs.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Hantracker">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/hantracker" icon={iconGit}
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
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/death-calendar" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			Inspired by the <ExternalLink href="https://waitbutwhy.com/2014/05/life-weeks.html"
				>waitbutwhy.com</ExternalLink
			> article, I decided to create a visual representation of how much time you have until your ultimate
			demise. It's designed to help you spend more time on the things that really matter to you.
		</p>
	</PortfolioPiece>
	<PortfolioPiece name="Factorio Blueprint String Creator">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/blueprint-string-creator" icon={iconGit}
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
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/feedback-box-component" icon={iconGit}
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
			{#snippet iconGlobe()}
				<svg inline-src="globe" width="1.5em" height="1.5em" />
			{/snippet}
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://hexagram-svg.netlify.app/" icon={iconGlobe}>Demo</PortfolioLink>
			<PortfolioLink href="https://github.com/wildwestrom/hexagram-svg-generator" icon={iconGit}
				>{NAME_FOR_REPOSITORY}</PortfolioLink
			>
		</ul>
		<p>
			The I-Ching is an ancient Chinese system of divination. It involves creating a figure of 6
			lines, broken or unbroken, usually by casting coins or picking yarrow stalks. This demo
			generates an SVG graphic based on the hexagram number.
		</p></PortfolioPiece
	>
	<PortfolioPiece name="Uniorg Util">
		<ul class="portfolio-links">
			{#snippet iconGit()}
				<svg inline-src="git" width="1.5em" height="1.5em" />
			{/snippet}
			<PortfolioLink href="https://github.com/wildwestrom/uniorg-util" icon={iconGit}
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

	p {
		line-height: 1.6;
	}

	svg {
		margin-right: 0.25rem;
	}
</style>
