package com.example.notesprovider.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.notesprovider.data.local.NOTES_TABLE
import com.example.notesprovider.data.local.NotesDao
import com.example.notesprovider.data.local.NotesDatabase
import androidx.core.net.toUri
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.example.notesprovider.data.local.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class NotesContentProvider: ContentProvider() {
    lateinit var notesDatabase: NotesDatabase
        private set
    private lateinit var notesDao : NotesDao
    companion object{
        const val AUTHORITY = "chaitnya.ghai.notes.provider"
//        pass authority as a string and table which u want to access/expose
        const val URL = "content://$AUTHORITY/$NOTES_TABLE"
        val CONTENT_URI = URL.toUri()
    }
//    it checks the whether the resolver req. for is valid or not
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY,NOTES_TABLE , 1) // gives access to all the rows/ entity
        addURI(AUTHORITY, "$NOTES_TABLE#", 2) // gives access to single row/ entity (based on id)
    }



    override fun onCreate(): Boolean {
        val context = context ?: return false
        notesDatabase = Room.databaseBuilder(context, NotesDatabase::class.java, "notes_database").build()
        notesDao = notesDatabase.getNotesDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            1 -> {
                val queryBuilder = SupportSQLiteQueryBuilder.builder(NOTES_TABLE)
                    .columns(projection)
                    .selection(selection, selectionArgs)
                    .orderBy(sortOrder)
                    .create()

                /**
                 * The query() function in a ContentProvider is synchronous
                 * runBlocking starts a coroutine
                 * but blocks the current thread until the coroutine finishes,
                 * so you can safely return the Cursor result
                 * */
                runBlocking(Dispatchers.IO) {
                    notesDatabase.query(queryBuilder)
                }

            }
            2 -> {
                val noteId = ContentUris.parseId(uri).toInt()
                val queryBuilder = SupportSQLiteQueryBuilder.builder(NOTES_TABLE)
                    .columns(projection)
                    .selection("${NoteEntity::id.name} = ?", arrayOf(noteId.toString()))
                    .orderBy(sortOrder)
                    .create()

                runBlocking(Dispatchers.IO) {
                    notesDatabase.query(queryBuilder)
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        when(uriMatcher.match(uri)){
            //vnd.android.cursor - fixed
            1 -> return "vnd.android.cursor.dir/$AUTHORITY.$NOTES_TABLE"
            2 -> return "vnd.android.cursor.item/$AUTHORITY.$NOTES_TABLE"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val noteEntity = NoteEntity(
            title = values?.getAsString("title").orEmpty(),
            desc = values?.getAsString(NoteEntity::desc.name).orEmpty()
        )
        val id = runBlocking(Dispatchers.IO) {
            notesDao.insert(noteEntity)
        }
        return ContentUris.withAppendedId(CONTENT_URI,id)
    }


    override fun delete(
        uri: Uri,
        p1: String?,
        p2: Array<out String?>?,
    ): Int {
        val noteId = ContentUris.parseId(uri).toInt()
        val noteEntity = runBlocking(Dispatchers.IO) { notesDao.getNoteById(noteId) }
        val deletedRow = runBlocking(Dispatchers.IO) { notesDao.delete(noteEntity) }
        return deletedRow
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        p2: String?,
        p3: Array<out String?>?,
    ): Int {
        val noteId = ContentUris.parseId(uri).toInt()
        val noteEntity = runBlocking(Dispatchers.IO) { notesDao.getNoteById(noteId) }
        val updateData = noteEntity.copy(
            title = values?.getAsString("title").orEmpty(),
            desc = values?.getAsString(NoteEntity::desc.name).orEmpty(),
            id = noteEntity.id
        )
        val id = runBlocking(Dispatchers.IO) { notesDao.update(updateData) }
        return id
    }
}