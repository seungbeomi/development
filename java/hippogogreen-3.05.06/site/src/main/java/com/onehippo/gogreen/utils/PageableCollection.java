/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.utils;

import java.util.ArrayList;
import java.util.List;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoDocumentIterator;

public class PageableCollection<T extends HippoBean> extends Pageable {
    public static final int DEFAULT_PAGE_FILL = 9;

    private List<HippoBean> items;

    //private static final PageableCollection EMPTY_IMMUTABLE = new PageableCollection(0, true);

    /**
     * Returns empty immutable collection
     */
    public PageableCollection(final HippoBeanIterator beans, final int currentPage) {
        super(beans.getSize(), currentPage);
        items = new ArrayList<HippoBean>();
    }

    public PageableCollection(final HippoBeanIterator beans,final int pageSize, final int currentPage) {
        super(beans.getSize(), currentPage, pageSize);
        items = new ArrayList<HippoBean>();
        process(beans);
    }
   public PageableCollection(final HippoDocumentIterator<HippoBean> beans,final int beanSize,final int pageSize, final int currentPage) {
        super(beanSize, currentPage, pageSize);
        items = new ArrayList<HippoBean>();
        process(beans);
    }

    public PageableCollection(int total, List<HippoBean> items) {
        super(total);
        this.items = items;
    }

    public PageableCollection(List<HippoBean> items, int pageSize, int currentPage) {
        super(items.size(), currentPage, pageSize);
        this.items = items;
    }

    public void addItem(HippoBean item) {
        items.add(item);
    }

    private void process(HippoBeanIterator beans) {
        items = new ArrayList<HippoBean>();
        int startAt = getStartOffset();
        if (startAt < getTotal()) {
            beans.skip(startAt);
        }
        int count = 0;
        while (beans.hasNext()) {
            if (count == getPageSize()) {
                break;
            }
            Object bean = beans.next();
            if(bean!=null)
            {
                items.add((HippoBean)bean);
                count++;
                
            }
        }
    }
    private void process(HippoDocumentIterator<HippoBean> beans) {
        items = new ArrayList<HippoBean>();
        int startAt = getStartOffset();
        if (startAt < getTotal()) {
            beans.skip(startAt);
        }
        int count = 0;
        while (beans.hasNext()) {
            if (count == getPageSize()) {
                break;
            }
            Object bean = beans.next();
            if(bean!=null)
            {
                items.add((HippoBean)bean);
                count++;

            }
        }
    }

    public List<? extends HippoBean> getItems() {
        return items;
    }

    public void setItems(List<HippoBean> items) {
        this.items = items;
    }

    /**
     * Default page range for given page
     *
     * @param page current page
     * @return page surrounded by results on both side e.g.
     *         {@literal 1,2,3,4<selected page>5, 6 ,7 ,8,9 etc.>}
     * @see #DEFAULT_PAGE_FILL
     */
    public List<Long> getPageRange(final int page) {
        return getPageRangeWithFill(page, DEFAULT_PAGE_FILL);
    }

    /**
     * Default Page range for current selected page, it is "google alike"
     * page range with x pages before selected item and x+1 after selected item.
     *
     * @return range based on default fill {@literal 1,2,3,4,5 <selected 6>, 7, 8,9 etc. }
     * @see #DEFAULT_PAGE_FILL
     */
    public List<Long> getCurrentRange() {
        return getPageRangeWithFill(getCurrentPage(), DEFAULT_PAGE_FILL);
    }

    /**
     * Return previous X and next X pages for given page, based on total pages.
     *
     * @param page   selected page
     * @param fillIn selected page
     * @return page range for given page
     */
    public List<Long> getPageRangeWithFill(long page, final int fillIn) {
        long currentPage=0;
        final List<Long> pages = new ArrayList<Long>();
        // do bound checking
        if (page < 0) {
            currentPage = 1;
        }
        if (page > getTotalPages()) {
            currentPage = getTotalPages();
        }
        // fill in lower range: e.g. for 2 it will  be 1
        long start = currentPage - fillIn;
        if (start <= 0) {
            start = 1;
        }
        // end part:
        long end = currentPage + fillIn + 1;
        if (end > getTotalPages()) {
            end = getTotalPages();
        }
        for (long i = start; i <= end; i++) {
            pages.add(i);
        }
        return pages;
    }
}
