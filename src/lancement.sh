cd sae_2.03_grp_08/src/bataillenavale

javac @compile.list -encoding utf-8

cd sae_2.03_grp_08/src

echo "nom du serveur : " $(hostname)

java bataillenavale.Serveur
