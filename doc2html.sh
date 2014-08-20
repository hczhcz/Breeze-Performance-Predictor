echo "<!DOCTYPE html>" > doc_radlab.html
echo "<html>" > doc_radlab.html
echo "<head>" >> doc_radlab.html
echo "<meta http-equiv='content-type' content='text/html; charset=utf-8' />" >> doc_radlab.html
echo "</head>" >> doc_radlab.html
echo "<body>" >> doc_radlab.html
hsmarkdown doc_radlab.md >> doc_radlab.html
echo "</body>" >> doc_radlab.html
firefox doc_radlab.html
