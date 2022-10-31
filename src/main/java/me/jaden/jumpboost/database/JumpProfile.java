package me.jaden.jumpboost.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import me.jaden.jumpboost.JumpBoost;

public class JumpProfile {
    private UUID uuid;
    private int jumpLevel = 1;

    public int getJumpLevel() {
        return Math.max(1, jumpLevel);
    }

    public int incrementJumpLevel() {
        return jumpLevel++;
    }

    public int decrementJumpLevel() {
        return Math.max(1, jumpLevel--);
    }

    public JumpProfile load(UUID uuid) {
        try {
            PreparedStatement preparedStatement = JumpBoost.getPlugin().getMySQL().getConnection().prepareStatement("SELECT * FROM `jumpboost` WHERE `uuid` = ?");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.executeQuery();
            this.jumpLevel = preparedStatement.getResultSet().getInt("jumplevel");
        } catch (SQLException e) {
            e.printStackTrace();
            this.uuid = uuid;
            this.jumpLevel = 0;
        }
        return this;
    }

    public PreparedStatement getSaveStatement() throws SQLException {
        PreparedStatement preparedStatement = JumpBoost.getPlugin().getMySQL().getConnection().prepareStatement("INSERT INTO `jumpboost` (`uuid`, `jumplevel`) VALUES (?, ?)");
        preparedStatement.setString(1, this.uuid.toString());
        preparedStatement.setInt(2, this.jumpLevel);
        return preparedStatement;
    };
}
