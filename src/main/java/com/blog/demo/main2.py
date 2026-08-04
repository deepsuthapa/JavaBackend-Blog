from docling.document_converter import DocumentConverter

source = "https://www.studocu.com/in/document/pragati-engineering-college/human-computer-interaction/ml-unit-1-to-5/56353144"

converter = DocumentConverter()
result = converter.convert(source)

print(result.document.export_to_markdown())
