package br.com.techz.jardimviver.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.techz.jardimviver.model.ResponsavelDTO;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "jardim_viver.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RESPONSAVEL = "responsavel";
    private static final String COL_ID = "id";
    private static final String COL_NOME = "nome";
    private static final String COL_SOBRENOME = "sobrenome";
    private static final String COL_EMAIL = "email";
    private static final String COL_IDADE = "idade";
    private static final String COL_ENDERECO = "endereco";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_RESPONSAVEL + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME + " TEXT NOT NULL, " +
                COL_SOBRENOME + " TEXT NOT NULL, " +
                COL_EMAIL + " TEXT NOT NULL, " +
                COL_IDADE + " INTEGER NOT NULL, " +
                COL_ENDERECO + " TEXT NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSAVEL);
        onCreate(db);
    }

    public long inserir(ResponsavelDTO responsavel) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_RESPONSAVEL, null, toValues(responsavel));
    }

    public int atualizar(ResponsavelDTO responsavel) {
        SQLiteDatabase db = getWritableDatabase();
        return db.update(
                TABLE_RESPONSAVEL,
                toValues(responsavel),
                COL_ID + " = ?",
                new String[]{String.valueOf(responsavel.getId())}
        );
    }

    public int excluir(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_RESPONSAVEL, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public List<ResponsavelDTO> listarTodos() {
        List<ResponsavelDTO> responsaveis = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.query(
                TABLE_RESPONSAVEL,
                null,
                null,
                null,
                null,
                null,
                COL_NOME + " ASC, " + COL_SOBRENOME + " ASC")) {

            int idIndex = cursor.getColumnIndexOrThrow(COL_ID);
            int nomeIndex = cursor.getColumnIndexOrThrow(COL_NOME);
            int sobrenomeIndex = cursor.getColumnIndexOrThrow(COL_SOBRENOME);
            int emailIndex = cursor.getColumnIndexOrThrow(COL_EMAIL);
            int idadeIndex = cursor.getColumnIndexOrThrow(COL_IDADE);
            int enderecoIndex = cursor.getColumnIndexOrThrow(COL_ENDERECO);

            while (cursor.moveToNext()) {
                responsaveis.add(new ResponsavelDTO(
                        cursor.getLong(idIndex),
                        cursor.getString(nomeIndex),
                        cursor.getString(sobrenomeIndex),
                        cursor.getString(emailIndex),
                        cursor.getInt(idadeIndex),
                        cursor.getString(enderecoIndex)
                ));
            }
        }

        return responsaveis;
    }

    private ContentValues toValues(ResponsavelDTO responsavel) {
        ContentValues values = new ContentValues();
        values.put(COL_NOME, responsavel.getNome());
        values.put(COL_SOBRENOME, responsavel.getSobrenome());
        values.put(COL_EMAIL, responsavel.getEmail());
        values.put(COL_IDADE, responsavel.getIdade());
        values.put(COL_ENDERECO, responsavel.getEndereco());
        return values;
    }
}
