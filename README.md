How It Works

File Menu — Open loads a file into the text area using BufferedReader; Save writes the current text using BufferedWriter
Edit Menu — Find & Replace opens a modal dialog; clicking "Replace All" calls String.replace() across the full text area content
File Filter — The file chooser is filtered to .txt files only for a clean UX
Swing EDT — The app is launched on the Event Dispatch Thread via SwingUtilities.invokeLater for thread safety


License
This project is open source and available under the MIT License.
