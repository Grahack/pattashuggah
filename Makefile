dev:
	lein watch
prod:
	chromium-browser --headless --dump-dom http://localhost:8280/ > index.html
	sed '1d'  -i index.html
	sed '12d' -i index.html
