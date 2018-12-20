package com.example.mk0730.alkolmetre.db.model;

import android.content.ContentResolver;
import android.net.Uri;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(indexes = { @Index(value = "query", unique = true) })
public class ApiQuery extends BaseModel {
    public static final String PATH = "query";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
            CONTENT_AUTHORITY + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
            CONTENT_AUTHORITY + "/" + PATH;

    @Id
    private Long id;

    @NotNull
    private String query;
    private Boolean isFinalPage;
    private Integer totalItemCount;

    @ToMany(referencedJoinProperty = "apiQueryId")
    private List<ApiQueryResult> apiQueryResults;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1300928456)
    private transient ApiQueryDao myDao;

    @Generated(hash = 505603565)
    public ApiQuery(Long id, @NotNull String query, Boolean isFinalPage, Integer totalItemCount) {
        this.id = id;
        this.query = query;
        this.isFinalPage = isFinalPage;
        this.totalItemCount = totalItemCount;
    }

    @Generated(hash = 959004163)
    public ApiQuery() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 483609562)
    public List<ApiQueryResult> getApiQueryResults() {
        if (apiQueryResults == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ApiQueryResultDao targetDao = daoSession.getApiQueryResultDao();
            List<ApiQueryResult> apiQueryResultsNew = targetDao
                    ._queryApiQuery_ApiQueryResults(id);
            synchronized (this) {
                if (apiQueryResults == null) {
                    apiQueryResults = apiQueryResultsNew;
                }
            }
        }
        return apiQueryResults;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 411347037)
    public synchronized void resetApiQueryResults() {
        apiQueryResults = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Boolean getIsFinalPage() {
        return this.isFinalPage;
    }

    public void setIsFinalPage(Boolean isFinalPage) {
        this.isFinalPage = isFinalPage;
    }

    public Integer getTotalItemCount() {
        return this.totalItemCount;
    }

    public void setTotalItemCount(Integer totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 534811371)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getApiQueryDao() : null;
    }
}
