dev:
	npm run watch
prod:
	chromium --headless --dump-dom http://localhost:8280/ > index.html
	cp resources/public/style.css .
	cp resources/public/screen.css .
	cp resources/public/print.css .
	git add index.html style.css screen.css print.css
	git commit -m "Update prod"
	git push origin main
