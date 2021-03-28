package com.example.pts4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;


public abstract class Piece {

    ImageView imageView;
    Case aCase;
    boolean isBlack;
    Echiquier echiquier;
    List<Case> list; // Liste des coups possibles
    Case cases[][]; //Liste des cases de l'échiquier
    boolean isOnClick = false;
    boolean firstMove = true;
    ConstraintLayout layout;

    /**
     * Instanciation de la piece
     * @param aCase Case ou se trouve la piece
     * @param context Context de la piece
     * @param layout Layout ou se trouve la piece
     * @param isBlack Vrai si la piece est noire
     * @param echiquier Echiquier ou se trouve la piece
     */
    public Piece(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        aCase.setPiece(this);
        this.aCase = aCase;
        this.layout = layout;
        cases = echiquier.getCases();
        this.echiquier = echiquier;
        imageView = new ImageView(context);
        if (isBlack) imageView.setRotation(180);
        this.isBlack = isBlack;
    }

    /**
     * Permet d'afficher les déplacements possibles.
     * Cela coloriera les cases en vert
     * Voila comment se déroule généralement la méthode :
     *         On met une action quand on clique sur la piece
     *         Si la case n'es pas déjà cliqué (onClick), pour chaque case ou le déplacement est possible
     *         si la case à une piece, on permet la prise,
     *         sinon on mes un onClickListenner sur la case pour permettre le déplacement lorqu'on cliquera dessus
     *         Sinon si le onClick est à false on désactive tout les onClickListenner et on recolorie les case avec leur couleur d'origine
     */
    public abstract void showDeplacement();

    /**
     * Déplace une pièce dans une case
     * @param moovingCase Case ou la piece va
     */
    public void deplacement(Case moovingCase) {
        if (moovingCase.piece != null) moovingCase.piece.deletePiece();
        aCase.setPiece(null);
        moovingCase.setPiece(this);
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
        echiquier.reset();
        for (Case laCase : list) {
            laCase.clickable(true);
        }
        echiquier.manche(isBlack);

    }

    /**
     * Déplacement pour effectuer un rock (échange entre roi et tour)
     * @param moovingCase
     */
    public void deplacementRocking(Case moovingCase) {
        aCase.setPiece(null);
        moovingCase.setPiece(this);
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
        echiquier.reset();
        echiquier.manche(isBlack);

    }

    /**
     * Déplacement sur une case ou se trouve une piece ennemie
     * @param priseCase
     */
    public void prise(Case priseCase) {
        priseCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //priseCase.piece.deletePiece();
                priseCase.piece.imageView.setVisibility(View.INVISIBLE);
                deplacement(priseCase);
                firstMove = false;

            }
        });
    }


    public Case getCase() {
        return aCase;
    }

    public Piece getPiece() {
        return this;
    }

    /**
     * Donne liste des cases ou la piece peut se déplacer sans mettre son roi en echec
     * La méthode est composé de différentes boucles if qui vérifierons que la piece à le droit ou non d'aller dans certaines cases
     * @return liste des cases
     */
    public abstract List<Case> getListOfPossibleCases();

    /**
     * Donne liste des cases ou la piece peut se déplacer même si cela met en echec son roi
     * @return liste des cases prenables
     */
    public abstract List<Case> getListOfPossibleTaken();

    /**
     * Supprimer une piece.
     * Elle supprimera la piece mais l'enlevera aussi de la liste de l'échiquier
     */
    public void deletePiece() {
        layout.removeView(imageView);
        imageView = null;
        aCase.setPiece(null);
        aCase = null;
        if (isBlack)
            echiquier.noirs.remove(this);
        else
            echiquier.blancs.remove(this);
        echiquier = null;

    }

    /**
     * Permet de savoir si une piece à le droit du bougé sur une case (getListeOfPossibleCases) sans mettre son roi en echec
     * @param maCase
     * @return
     */
    public boolean canMove(Case maCase) {
        Case original = getCase();

        Piece pOriginal = null;
        if (maCase.piece != null) {
            pOriginal = maCase.piece;
            if (pOriginal.isBlack) echiquier.noirs.remove(pOriginal);
            else echiquier.blancs.remove(pOriginal);
        }
        aCase.piece = null;
        aCase = maCase;

        maCase.piece = this;
        if (isBlack) {
            if (echiquier.echecBlanc(echiquier.roiN.getCase())) {
                aCase = original;
                if (pOriginal != null) {
                    if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
                    else echiquier.blancs.add(pOriginal);
                }
                maCase.piece = pOriginal;
                aCase.piece = this;
                return false;
            }
        } else {
            if (echiquier.echecNoir(echiquier.roiB.getCase())) {
                aCase = original;
                if (pOriginal != null) {
                    if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
                    else echiquier.blancs.add(pOriginal);
                }
                maCase.piece = pOriginal;
                aCase.piece = this;
                return false;
            }
        }
        aCase = original;
        if (pOriginal != null) {
            if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
            else echiquier.blancs.add(pOriginal);
        }
        maCase.piece = pOriginal;
        aCase.piece = this;
        return true;

    }
}
