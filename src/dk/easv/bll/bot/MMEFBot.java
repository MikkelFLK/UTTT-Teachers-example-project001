/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.easv.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.GameManager;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import java.util.List;
import java.util.Random;

/**
 *
 * @author MikkelK
 */
public class MMEFBot implements IBot
{

    private static final String BOTNAME = "MMEF";

    @Override
    public IMove doMove(IGameState state)
    {
        Random r = new Random();
        String myID = "" + (state.getMoveNumber() % 2);
        List<IMove> validMoves = state.getField().getAvailableMoves();
        for (int i = 0; i < validMoves.size(); i++)
        {

            IMove choosenMove = validMoves.get(i);

            state.getField().getBoard()[choosenMove.getX()][choosenMove.getY()] = myID;
            boolean isWin = GameManager.isWin(state.getField().getBoard(), choosenMove, myID);
            if (isWin)
            {
                return choosenMove;
            }
            //undo
            state.getField().getBoard()[choosenMove.getX()][choosenMove.getY()] = IField.EMPTY_FIELD;
        }

        return validMoves.get(r.nextInt(validMoves.size()));
    }

    @Override
    public String getBotName()
    {

        return BOTNAME;
    }
}
