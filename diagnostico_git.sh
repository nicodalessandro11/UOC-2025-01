#!/bin/bash

echo "========== ARCHIVOS MÁS PESADOS =========="
find . -type f ! -path "./.git/*" -exec du -h {} + | sort -rh | head -n 20

echo ""
echo "========== CANTIDAD DE ARCHIVOS POR DIRECTORIO =========="
find . -type f ! -path "./.git/*" | cut -d/ -f2 | sort | uniq -c | sort -nr | head -n 20

echo ""
echo "========== ARCHIVOS NO RASTREADOS =========="
git ls-files --others --exclude-standard | head -n 20
echo "(Mostrando solo los primeros 20. Puede haber más...)"

echo ""
echo "========== ARCHIVOS TRACKED MODIFICADOS =========="
git status --short | grep '^ M' | wc -l

echo ""
echo "========== ESTADO DEL REPO =========="
git status -uno
